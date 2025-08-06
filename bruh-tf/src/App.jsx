import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Card from './component/Card'
import ProductList from './component/ProductList'
 const sample = [
    {
      id: 1,
    name: "Palm Sugar",
    description: "Sweet natural sugar from Kampong Speu",
    price: "$3.00",
    category: "food",
    imageUrl: "https://placehold.co/600x400",
    telegramLink: "https://t.me/seller1" 
    },
     {
    id: 2,
    name: "Khmer Scarf",
    description: "Traditional handmade scarf",
    price: "$7.00",
    category: "clothing",
    imageUrl: "https://placehold.co/600x400",
    telegramLink: "https://t.me/seller2"
  }
  ];

function App() {
 
  return (
    <>
      <Card></Card>
      {/* <ProductList products={sample}></ProductList> */}
    </>
  )
}

export default App
