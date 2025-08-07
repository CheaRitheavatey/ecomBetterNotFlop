import { useState } from "react";
import CategoryNavigation from "./component/CategoryNavigation";
import FeaturedProducts from "./component/FeaturedProducts";
import Footer from "./component/Footer";
import Header from "./component/Header";
import HeroSection from "./component/HeroSection";
import ProductsGrid from "./component/ProductsGrid";
import { useProducts } from "./hooks/useProducts";
import { CATEGORIES } from "./types";



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
    <div>
      <Header
        searchTerm={searchTerm}
        setSearchTerm={setSearchTerm}
        showFilter={showFilters}
        setShowFilter={setShowFilters}
        />

      <HeroSection
        setSelectedCategory={setSelectedCategory}
      />

      {selectedCategory === CATEGORIES.ALL && (
        <FeaturedProducts products={featuredProducts} />
      )}
      
      <CategoryNavigation
        selectedCategory={selectedCategory}
        setSelectedCategory={setSelectedCategory}
      ></CategoryNavigation>


      <ProductsGrid
        products={filteredProducts}
        selectedCategory={selectedCategory}
      />

      <Footer />
    </div>
  );
}
export default App;
