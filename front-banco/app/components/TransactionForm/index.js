
'use client';
import { useState } from 'react';
import { API_URL } from '../../assets/constants';


const TransactionForm = () => {
    const [accountId, setAccountId] = useState('');
    const [amount, setAmount] = useState('');
    const [transactionType, setTransactionType] = useState('DEPOSITO'); 
    const [transactionTypeUrl, setTransactionTypeUrl] = useState('deposit'); 
    const [message, setMessage] = useState('');

    const handleTransaction = async (e) => {
        e.preventDefault();
        const url = `${API_URL}/accounts/${accountId}/${transactionTypeUrl}`;
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ monto: amount }),
        });

        if (response.ok) {
            setMessage(`${transactionType === 'DEPOSITO' ? 'Depósito' : 'Retiro'} realizado con éxito.`);
        } else {
            setMessage('Error al realizar la transacción.');
        }
        setAmount('');
    };

    return (
        <div className="flex flex-col space-y-8">
            <form onSubmit={handleTransaction} className="flex flex-col space-y-4">
                <h2 className="text-xl font-bold">Transacción</h2>
                <label>
                    ID de Cuenta:
                    <input
                        type="number"
                        value={accountId}
                        onChange={(e) => setAccountId(e.target.value)}
                        required
                        className="border p-2 rounded"
                    />
                </label>
                <label>
                    Monto:
                    <input
                        type="number"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                        required
                        className="border p-2 rounded"
                    />
                </label>
                <label>
                    Tipo de Transacción:
                    <select
                        value={transactionType}
                        onChange={(e) => {
                            setTransactionType(e.target.value)
                            if (transactionType != 'DEPOSITO'){
                                setTransactionTypeUrl('deposit');
                            } else{
                                setTransactionTypeUrl('withdraw');

                            }
                        }}
                        className="border p-2 rounded"
                    >
                        <option value="DEPOSITO">Depositar</option>
                        <option value="RETIRO">Retirar</option>
                    </select>
                </label>
                <button type="submit" className="bg-blue-500 text-white p-2 rounded">
                    {transactionType === 'DEPOSITO' ? 'Depositar' : 'Retirar'}
                </button>
            </form>

            {message && <p className="text-green-600">{message}</p>}
        </div>
    );
};

export default TransactionForm;
