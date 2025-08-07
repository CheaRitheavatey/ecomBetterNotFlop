import { useState } from 'react';
import Header from './components/Header.jsx';
import HeroSection from './components/HeroSection.jsx';
import FeaturedProducts from './components/FeaturedProducts.jsx';
import CategoryNavigation from './components/CategoryNavigation.jsx';
import ProductsGrid from './components/ProductsGrid.jsx';
import Footer from './components/Footer.jsx';
import { useProducts } from './hooks/useProducts.js';
import { CATEGORIES } from './types/index.js';

function App() {
  const [showFilters, setShowFilters] = useState(false);
  const {
    selectedCategory,
    setSelectedCategory,
    searchTerm,
    setSearchTerm,
    filteredProducts,
    featuredProducts
  } = useProducts();

  return (
    <div className="min-h-screen bg-gray-50">
      <Header 
        searchTerm={searchTerm}
        setSearchTerm={setSearchTerm}
        showFilters={showFilters}
        setShowFilters={setShowFilters}
      />

      <HeroSection setSelectedCategory={setSelectedCategory} />

      {selectedCategory === CATEGORIES.ALL && (
        <FeaturedProducts products={featuredProducts} />
      )}

      <CategoryNavigation 
        selectedCategory={selectedCategory}
        setSelectedCategory={setSelectedCategory}
      />

      <ProductsGrid 
        products={filteredProducts}
        selectedCategory={selectedCategory}
      />

      <Footer />
    </div>
  );
}

export default App;