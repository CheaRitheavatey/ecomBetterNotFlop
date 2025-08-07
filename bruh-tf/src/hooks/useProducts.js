import { useState, useMemo } from 'react';
import { sampleProducts } from '../data/products.js';
import { CATEGORIES } from '../types/index.js';

export const useProducts = () => {
  const [selectedCategory, setSelectedCategory] = useState(CATEGORIES.ALL);
  const [searchTerm, setSearchTerm] = useState('');

  const filteredProducts = useMemo(() => {
    return sampleProducts.filter(product => {
      const matchesCategory = selectedCategory === CATEGORIES.ALL || product.category === selectedCategory;
      const matchesSearch = product.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                          product.description.toLowerCase().includes(searchTerm.toLowerCase()) ||
                          product.seller.name.toLowerCase().includes(searchTerm.toLowerCase());
      return matchesCategory && matchesSearch;
    });
  }, [selectedCategory, searchTerm]);

  const featuredProducts = useMemo(() => {
    return sampleProducts.filter(product => product.featured);
  }, []);

  return {
    selectedCategory,
    setSelectedCategory,
    searchTerm,
    setSearchTerm,
    filteredProducts,
    featuredProducts
  };
};