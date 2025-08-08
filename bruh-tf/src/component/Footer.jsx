import { MapPin, MessageCircle, Mail, Phone } from 'lucide-react';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        <div className="footer-grid">
          <div className="footer-about">
            <div className="footer-brand">
              <div className="footer-logo">
                <MapPin className="footer-logo-icon" />
              </div>
              <span className="footer-brand-name">Local Market</span>
            </div>
            <p className="footer-description">
              Connecting local producers with customers worldwide. Supporting local communities through direct trade and empowering artisans with technology.
            </p>
            <div className="footer-contact-info">
              <div className="contact-item">
                <Mail className="contact-icon" />
                <span className="contact-text">info@ruralmarket.com</span>
              </div>
              <div className="contact-item">
                <Phone className="contact-icon" />
                <span className="contact-text">+1 (555) 123-4567</span>
              </div>
            </div>
          </div>
          
          <div className="footer-links">
            <h3 className="footer-heading">Categories</h3>
            <ul className="footer-menu">
              <li><a href="#" className="footer-link">Food & Beverages</a></li>
              <li><a href="#" className="footer-link">Clothing</a></li>
              <li><a href="#" className="footer-link">Gifts & Crafts</a></li>
              <li><a href="#" className="footer-link">All Products</a></li>
            </ul>
          </div>
          
          <div className="footer-sellers">
            <h3 className="footer-heading">For Sellers</h3>
            <p className="seller-text">
              Want to sell your products? Join our community of rural producers.
            </p>
            <button className="seller-button">
              <MessageCircle className="button-icon" />
              <span>Join as Seller</span>
            </button>
          </div>
        </div>
        
        <div className="footer-copyright">
          <p>&copy; 2025 RuralMarket. Supporting local producers worldwide.</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;