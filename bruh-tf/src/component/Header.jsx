import { Search, MapPin, Menu, X, User, Globe } from 'lucide-react';
import { useState } from 'react';

const Header = ({ searchTerm, setSearchTerm }) => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);
  const [isSignUp, setIsSignUp] = useState(false);
  const [selectedLanguage, setSelectedLanguage] = useState('EN');
  const [isLanguageDropdownOpen, setIsLanguageDropdownOpen] = useState(false);
  const [isProvinceDropdownOpen, setIsProvinceDropdownOpen] = useState(false);

  const languages = [
    { code: 'EN', name: 'English' },
    { code: 'KH', name: 'ខ្មែរ' }
  ];

  const provinces = [
    'Siem Reap',
    'Takeo', 
    'Kompot'
  ];

  const handleLanguageSelect = (language) => {
    setSelectedLanguage(language.code);
    setIsLanguageDropdownOpen(false);
  };

  return (
    <>
      {/* Main Header */}
      <header className="header">
        <div className="header-container">
          <div className="header-content">
            
            {/* Logo */}
            <div className="logo-container">
              <div className="logo-icon">
                <MapPin className="logo-icon-svg" />
              </div>
              <div className="logo-text">
                <h1 className="logo-title">Local Market</h1>
                <p className="logo-subtitle">Supporting Local Producers</p>
              </div>
            </div>

            {/* Desktop Navigation */}
            <div className="desktop-nav">
              
              {/* Language Selector */}
              <div className="language-selector">
                <button
                  className="language-button"
                  onClick={() => setIsLanguageDropdownOpen(!isLanguageDropdownOpen)}
                >
                  <Globe className="language-icon" />
                  <span className="language-text">
                    {languages.find(lang => lang.code === selectedLanguage)?.flag} {selectedLanguage}
                  </span>
                </button>
                
                {isLanguageDropdownOpen && (
                  <div className="language-dropdown">
                    {languages.map((language) => (
                      <button
                        key={language.code}
                        className="language-option"
                        onClick={() => handleLanguageSelect(language)}
                      >
                        <span className="language-flag">{language.flag}</span>
                        <span className="language-name">{language.name}</span>
                      </button>
                    ))}
                  </div>
                )}
              </div>

              {/* Search Bar */}
              <div className="search-container">
                <Search className="search-icon" />
                <input
                  type="text"
                  className="search-input"
                  placeholder="Search products..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                />
              </div>

              {/* Navigation Links */}
              <nav className="nav-links">
                <a href="#" className="nav-link">
                  About
                </a>
                
                {/* Province Dropdown */}
                <div className="province-dropdown-container">
                  <button
                    className="province-button"
                    onClick={() => setIsProvinceDropdownOpen(!isProvinceDropdownOpen)}
                  >
                    Province
                  </button>
                  
                  {isProvinceDropdownOpen && (
                    <div className="province-dropdown">
                      {provinces.map((province) => (
                        <a
                          key={province}
                          href="#"
                          className="province-option"
                        >
                          {province}
                        </a>
                      ))}
                    </div>
                  )}
                </div>
              </nav>
            </div>

            {/* Login Button */}
            <div className="login-container">
              <button
                className="login-button"
                onClick={(e) => { e.stopPropagation();
                                setIsLoginModalOpen(true)}}
              >
                <User className="login-icon" />
                <span>Login</span>
              </button>
            </div>

            {/* Mobile Hamburger Button */}
            <div className="mobile-menu-button">
              <button 
                className="menu-toggle"
                onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
              >
                {isMobileMenuOpen ? (
                  <X className="menu-icon" />
                ) : (
                  <Menu className="menu-icon" />
                )}
              </button>
            </div>
          </div>
        </div>

        {/* Mobile Menu */}
        {isMobileMenuOpen && (
          <div className="mobile-menu">
            <div className="mobile-menu-content">
              
              {/* Mobile Search */}
              <div className="mobile-search-container">
                <Search className="mobile-search-icon" />
                <input
                  type="text"
                  className="mobile-search-input"
                  placeholder="Search products..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                />
              </div>

              {/* Mobile Language Selector */}
              <div className="mobile-language-selector">
                <span className="mobile-language-label">Language</span>
                <div className="mobile-language-buttons">
                  {languages.map((language) => (
                    <button
                      key={language.code}
                      className={`mobile-language-button ${selectedLanguage === language.code ? 'active' : ''}`}
                      onClick={() => setSelectedLanguage(language.code)}
                    >
                      {language.flag} {language.code}
                    </button>
                  ))}
                </div>
              </div>

              {/* Mobile Navigation */}
              <nav className="mobile-nav-links">
                <a href="#" className="mobile-nav-link">
                  About
                </a>
                
                <div className="mobile-province-container">
                  <p className="mobile-province-label">Province</p>
                  <div className="mobile-province-options">
                    {provinces.map((province) => (
                      <a
                        key={province}
                        href="#"
                        className="mobile-province-option"
                      >
                        {province}
                      </a>
                    ))}
                  </div>
                </div>
              </nav>

              {/* Mobile Login Button */}
              <button
                className="mobile-login-button"
                onClick={() => {
                  setIsLoginModalOpen(true);
                  setIsMobileMenuOpen(false);
                }}
              >
                <User className="mobile-login-icon" />
                <span>Login</span>
              </button>
            </div>
          </div>
        )}
      </header>

      {isLoginModalOpen && (
  <div className="modal-overlay">
    <div className="modal-container">
      <div className="modal-header">
        <h2 className="modal-title">{isSignUp ? 'Sign Up' : 'Sign In'}</h2>
        <button className="modal-close-button" onClick={() => setIsLoginModalOpen(false)}>
          <X className="icon" />
        </button>
      </div>

      <div className="modal-content">
        <div className="modal-icon-wrapper">
          <div className="modal-icon-circle">
            <User className="icon-large" />
          </div>
        </div>

        <form className="form">
          <div className="form-group">
            <label className="form-label">Full Name</label>
            <input type="text" className="form-input" placeholder="Enter your full name" />
          </div>

          <div className="form-group">
            <label className="form-label">Telegram Phone Number</label>
            <input type="tel" className="form-input" placeholder="+855 XX XXX XXX" />
          </div>

          <div className="form-group">
            <label className="form-label">Password</label>
            <input type="password" className="form-input" placeholder="Enter your password" />
          </div>

          {isSignUp && (
            <div className="form-group">
              <label className="form-label">Confirm Password</label>
              <input type="password" className="form-input" placeholder="Confirm your password" />
            </div>
          )}

          <button type="submit" className="form-submit">
            {isSignUp ? 'Sign Up' : 'Sign In'}
          </button>
        </form>

        <div className="form-footer">
          <p>
            {isSignUp ? 'Already have an account?' : "Don't have an account?"}
            <button className="form-toggle" onClick={() => setIsSignUp(!isSignUp)}>
              {isSignUp ? 'Sign In' : 'Sign Up'}
            </button>
          </p>
        </div>
      </div>
    </div>
  </div>
)}

{(isLanguageDropdownOpen || isProvinceDropdownOpen) && (
  <div
    className="dropdown-backdrop"
    onClick={() => {
      setIsLanguageDropdownOpen(false);
      setIsProvinceDropdownOpen(false);
    }}
  />
)}

    </>
  );
};

export default Header;