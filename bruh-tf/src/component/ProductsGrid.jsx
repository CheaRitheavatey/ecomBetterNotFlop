import { Search } from 'lucide-react';
import ProductCard from './ProductCard.jsx';
import { categories } from '../data/categories.js';

const ProductsGrid = ({ products, selectedCategory }) => {
  const categoryName = selectedCategory === 'all' 
    ? 'All Products' 
    : categories.find(c => c.id === selectedCategory)?.name;

  if (products.length === 0) {
    return (
      <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pb-16">
        <div className="text-center py-16">
          <div className="w-24 h-24 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <Search className="w-8 h-8 text-gray-400" />
          </div>
          <h3 className="text-xl font-semibold text-gray-900 mb-2">No products found</h3>
          <p className="text-gray-600">Try adjusting your search or filter criteria</p>
        </div>
      </section>
    );
  }

  return (
    <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pb-16">
      <div className="flex items-center justify-between mb-8">
        <h3 className="text-2xl font-bold text-gray-900">
          {categoryName}
        </h3>
        <span className="text-gray-600">{products.length} products found</span>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
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