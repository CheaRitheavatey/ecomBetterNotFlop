import { ShoppingBag, Coffee, Shirt, Gift } from 'lucide-react';
import { CATEGORIES } from '../types/index.js';

export const categories = [
  { 
    id: CATEGORIES.ALL, 
    name: 'All Products', 
    icon: ShoppingBag 
  },
  { 
    id: CATEGORIES.FOOD_BEV, 
    name: 'Food & Beverages', 
    icon: Coffee 
  },
  { 
    id: CATEGORIES.CLOTHING, 
    name: 'Clothing', 
    icon: Shirt 
  },
  { 
    id: CATEGORIES.GIFTS, 
    name: 'Gifts', 
    icon: Gift 
  }
];