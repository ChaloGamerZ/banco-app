'use client';
import { useState } from 'react';
import { API_URL } from '../../assets/constants';

const AccountForm = () => {
  const [titular, setTitular] = useState('');
  const [cuenta, setCuenta] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await fetch(`${API_URL}/accounts`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ titular }),
    });

    if (response.ok) {
      const data = await response.json();
      setCuenta(data);
      setTitular(''); 
    } else {
      const errorData = await response.json();
      console.error(errorData);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit} className="flex flex-col gap-4 p-4 border rounded-lg shadow-md bg-white text-black">
        <label className="block mb-2">
          Titular:
          <input
            type="text"
            value={titular}
            onChange={(e) => setTitular(e.target.value)}
            required
            className="border p-2 rounded w-full"
          />
        </label>
        <button type="submit" className="mt-2 bg-blue-500 text-white rounded p-2 transition duration-200 hover:bg-blue-600">
          Crear Cuenta
        </button>
      </form>
      
      {cuenta && (
        <div className="mt-4 p-4 border border-gray-300 rounded bg-gray-800">
          <h2 className="text-lg font-bold">Cuenta Creada</h2>
          <p><strong>ID:</strong> {cuenta.id}</p>
          <p><strong>Titular:</strong> {cuenta.titular}</p>
          <p><strong>Saldo:</strong> {cuenta.saldo}</p>
          <p><strong>Fecha de Creación:</strong> {new Date(cuenta.fechaCreacion).toLocaleString()}</p>
        </div>
      )}
    </div>
  );
};

export default AccountForm;
