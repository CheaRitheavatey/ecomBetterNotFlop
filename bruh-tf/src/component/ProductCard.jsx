import { MapPin, Star, MessageCircle } from 'lucide-react';
import { handleTelegramContact } from '../utils/telegram.js';

const ProductCard = ({ product, featured = false }) => {
  // data field
  const cardClasses = `product-card ${featured ? 'featured' : ''}`;
  const imageClasses = `product-image ${featured ? 'featured-image' : ''}`;
  const titleClasses = `product-title ${featured ? 'featured-title' : ''}`;
  const priceClasses = `product-price ${featured ? 'featured-price' : ''}`;
  const descClasses = `product-description ${featured ? 'featured-description' : ''}`;
  const locationClasses = `product-location ${featured ? 'featured-location' : ''}`;
  const ratingClasses = `product-rating ${featured ? 'featured-rating' : ''}`;
  const sellerClasses = `product-seller ${featured ? 'featured-seller' : ''}`;
  const buttonClasses = `contact-button ${featured ? 'featured-button' : ''}`;

  return (
    <div className={cardClasses}>
      <div className="product-image-container">
        <img 
          src={product.image} 
          alt={product.name}
          className={imageClasses}
        />
        {product.featured && (
          <div className={`featured-badge ${featured ? 'large-badge' : ''}`}>
            <span>New Product</span>
          </div>
        )}
      </div>
      
      <div className="product-content">
        <div className="product-header">
          <h4 className={titleClasses}>
            {product.name}
          </h4>
          <span className={priceClasses}>
            ${product.price}
          </span>
        </div>
        
        <p className={descClasses}>
          {product.description}
        </p>
        
        <div className="product-meta">
          <div className="location-info">
            <MapPin className="location-icon" />
            <span className={locationClasses}>
              {product.seller.location}
            </span>
          </div>
          <div className="rating-info">
            <Star className="rating-icon" />
            <span className={ratingClasses}>
              {product.seller.rating}
            </span>
          </div>
        </div>
        
        <div className={`product-footer ${featured ? 'flex-footer' : 'stacked-footer'}`}>
          <div className={sellerClasses}>
            <span>{product.seller.name}</span>
          </div>
          <button
            onClick={() => handleTelegramContact(product.seller.telegram)}
            className={buttonClasses}
          >
            <MessageCircle className="button-icon" />
            <span>Contact Seller</span>
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;