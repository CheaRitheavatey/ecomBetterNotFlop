import { MapPin, Star, MessageCircle } from 'lucide-react';
import { handleTelegramContact } from '../utils/telegram.js';

const ProductCard = ({ product, featured = false }) => {
  return (
    <div className="bg-white rounded-xl shadow-sm hover:shadow-lg transition-all duration-300 overflow-hidden group hover:-translate-y-1">
      <div className="relative overflow-hidden">
        <img 
          src={product.image} 
          alt={product.name}
          className={`w-full object-cover group-hover:scale-105 transition-transform duration-300 ${
            featured ? 'h-64' : 'h-48'
          }`}
        />
        {product.featured && (
          <div className={`absolute ${featured ? 'top-4 left-4' : 'top-3 left-3'}`}>
            <span className={`bg-red-500 text-white px-3 py-1 rounded-full font-medium ${
              featured ? 'text-sm' : 'text-xs'
            }`}>
              Featured
            </span>
          </div>
        )}
      </div>
      
      <div className={featured ? 'p-6' : 'p-4'}>
        <div className="flex items-start justify-between mb-3">
          <h4 className={`font-semibold text-gray-900 group-hover:text-emerald-600 transition-colors ${
            featured ? 'text-xl' : 'text-lg'
          }`}>
            {product.name}
          </h4>
          <span className={`font-bold text-emerald-600 ${
            featured ? 'text-2xl' : 'text-xl'
          }`}>
            ${product.price}
          </span>
        </div>
        
        <p className={`text-gray-600 mb-4 line-clamp-2 ${
          featured ? 'text-base' : 'text-sm'
        }`}>
          {product.description}
        </p>
        
        <div className={`flex items-center justify-between ${
          featured ? 'mb-4' : 'mb-3'
        }`}>
          <div className="flex items-center space-x-2">
            <MapPin className={featured ? 'w-4 h-4' : 'w-3 h-3'} />
            <span className={`text-gray-600 ${
              featured ? 'text-sm' : 'text-xs'
            }`}>
              {product.seller.location}
            </span>
          </div>
          <div className="flex items-center space-x-1">
            <Star className={`fill-yellow-400 text-yellow-400 ${
              featured ? 'w-4 h-4' : 'w-3 h-3'
            }`} />
            <span className={`font-medium text-gray-700 ${
              featured ? 'text-sm' : 'text-xs'
            }`}>
              {product.seller.rating}
            </span>
          </div>
        </div>
        
        <div className={featured ? 'flex items-center justify-between' : 'space-y-2'}>
          <div className={`text-gray-600 ${
            featured ? 'text-sm' : 'text-xs'
          }`}>
            <span className="font-medium">{product.seller.name}</span>
          </div>
          <button
            onClick={() => handleTelegramContact(product.seller.telegram)}
            className={`bg-emerald-600 hover:bg-emerald-700 text-white font-medium transition-all duration-200 flex items-center justify-center space-x-2 hover:scale-105 rounded-lg ${
              featured 
                ? 'px-6 py-2.5' 
                : 'w-full px-4 py-2 text-sm'
            }`}
          >
            <MessageCircle className="w-4 h-4" />
            <span>Contact Seller</span>
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;