'use client';
import { useState } from 'react';
import { API_URL } from '../../assets/constants';

const BalanceQuery = () => {
    const [accountId, setAccountId] = useState('');
    const [balance, setBalance] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(`${API_URL}/accounts/${accountId}/balance`);
        const response = await fetch(`${API_URL}/accounts/${accountId}/balance`);
        
        const data = await response.json();
        setBalance(data);
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col gap-4 p-4 border rounded-lg shadow-md bg-white text-black">
            <label className="flex flex-col">
                ID de Cuenta:
                <input
                    type="number"
                    value={accountId}
                    onChange={(e) => setAccountId(e.target.value)}
                    required
                    className="mt-1 border border-gray-300 rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
                />
            </label>
            <button
                type="submit"
                className="mt-2 bg-blue-500 text-white rounded p-2 transition duration-200 hover:bg-blue-600"
            >
                Consultar Saldo
            </button>
            {balance !== null && (
                <p className="mt-4 text-lg font-semibold">
                    Saldo: {balance}
                </p>
            )}
        </form>
    );
};

export default BalanceQuery;
