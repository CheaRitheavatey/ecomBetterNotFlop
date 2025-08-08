import { Search } from 'lucide-react';
import ProductCard from './ProductCard.jsx';
import { categories } from '../data/categories.js';

const ProductsGrid = ({ products, selectedCategory }) => {
  const categoryName = selectedCategory === 'all' 
    ? 'All Products' 
    : categories.find(c => c.id === selectedCategory)?.name;

  if (products.length === 0) {
    return (
      <section className="products-grid-empty">
        <div className="empty-state">
          <div className="empty-icon-container">
            <Search className="empty-icon" />
          </div>
          <h3 className="empty-title">No products found</h3>
          <p className="empty-message">Try adjusting your search or filter criteria</p>
        </div>
      </section>
    );
  }

  return (
    <section className="products-grid-container">
      <div className="products-header">
        <h3 className="products-title">
          {categoryName}
        </h3>
        <span className="products-count">{products.length} products found</span>
      </div>

      <div className="products-grid">
        {products.map((product) => (
          <ProductCard 
            key={product.id} 
            product={product} 
            featured={false}
          />
        ))}
      </div>
    </section>
  );
};

export default ProductsGrid;