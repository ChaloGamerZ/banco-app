'use client';
import AccountForm from './components/AccountForm';
import BalanceQuery from './components/BalanceQuery';
import TransactionForm from './components/TransactionForm';

export default function Home() {
  return (
    <article className='bg-gray-100 min-h-screen py-12'>
      <h1 className="text-3xl font-bold mb-8 text-black px-12">Banco App</h1>
    
      <div className="grid grid-cols-2 items-center justify-center place-items-center bg-gray-100 p-8">    
        <div className="bg-white shadow-md rounded-lg p-6 w-full max-w-md">
          <AccountForm />
        </div>
        <div className="bg-white shadow-md rounded-lg p-6 w-full max-w-md mt-4">
          <BalanceQuery />
        </div>
        <div className="bg-white shadow-md rounded-lg p-6 w-full max-w-md mt-4 text-black">
          <TransactionForm  />
        </div>
      </div>
    </article>
  );
}
