import ProductCard from './ProductCard.jsx';

const FeaturedProducts = ({ products }) => {
  if (!products || products.length === 0) return null;

  return (
    <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
      <div className="text-center mb-12">
        <h3 className="text-3xl font-bold text-gray-900 mb-4">Featured Products</h3>
        <p className="text-gray-600 max-w-2xl mx-auto">
          Handpicked selections from our most trusted local producers
        </p>
      </div>
      
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        {products.map((product) => (
          <ProductCard 
            key={product.id} 
            product={product} 
            featured={true}
          />
        ))}
      </div>
    </section>
  );
};

export default FeaturedProducts;