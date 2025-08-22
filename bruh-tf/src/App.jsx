import { useEffect, useState } from "react";
import CategoryNavigation from "./component/CategoryNavigation";
import FeaturedProducts from "./component/FeaturedProducts";
import Footer from "./component/Footer";
import Header from "./component/Header";
import HeroSection from "./component/HeroSection";
import ProductsGrid from "./component/ProductsGrid";
import { useProducts } from "./hooks/useProducts";
import { CATEGORIES } from "./types";



function App() {
  useEffect(() => {
    fetch("http://localhost:8080/api/product")
    .then((res) => res.json())
    .then((data) => setProducts(data))
    .catch((err) => console.error(err));
  }, [])
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

      {/* {selectedCategory === CATEGORIES.ALL && (
        <FeaturedProducts products={featuredProducts} />
      )} */}

      {/* <CategoryNavigation 
        selectedCategory={selectedCategory}
        setSelectedCategory={setSelectedCategory}
      /> */}

      <ProductsGrid 
        products={filteredProducts}
        selectedCategory={selectedCategory}
      />

      <Footer />
    </div>
  );
}

export default App;