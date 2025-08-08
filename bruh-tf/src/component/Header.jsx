import { Search, MapPin } from 'lucide-react';

const Header = ({ searchTerm, setSearchTerm }) => {
  return (
    <>
      {/* Main Header */}
      <header className="header-container">
        <div className="container header-content">
          <div className="d-flex align-items-center justify-content-between header-inner">
            <div className="d-flex align-items-center logo-container">
              <div className="logo-icon">
                <MapPin className="icon" />
              </div>
              <div className="logo-text">
                <h1 className="logo-title">Local Market</h1>
                <p className="logo-subtitle">Supporting Local Producers</p>
              </div>
            </div>
            
            <div className="d-none d-md-flex align-items-center header-controls">
              <nav className="main-nav">
                <ul className="nav-list">
                  <li className="nav-item">
                    <a href="#" className="nav-link">About</a>
                  </li>
                  <li className="nav-item dropdown">
                    <button className="dropdown-toggle">
                      Province <span className="dropdown-arrow"></span>
                    </button>
                    <ul className="dropdown-menu">
                      <li><a href="#" className="dropdown-item">Siem Reap</a></li>
                      <li><a href="#" className="dropdown-item">Takeo</a></li>
                      <li><a href="#" className="dropdown-item">Kompot</a></li>
                    </ul>
                  </li>
                </ul>
              </nav>
              
              <div className="search-container">
                <Search className="search-icon" />
                <input
                  type="text"
                  placeholder="Search products..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  className="search-input"
                />
              </div>
            </div>
          </div>
        </div>
      </header>

      {/* Mobile Search */}
      <div className="d-md-none mobile-search-container">
        <div className="search-container">
          <Search className="search-icon" />
          <input
            type="text"
            placeholder="Search products..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="search-input"
          />
        </div>
      </div>
    </>
  );
};

export default Header;