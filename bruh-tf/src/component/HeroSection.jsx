import { categories } from '../data/categories.js';

const HeroSection = ({ setSelectedCategory }) => {
  return (
    <section className="hero-section">
      <div className="container hero-content">
        <div className="hero-text">
          <h2 className="hero-title">
            Discover Local Treasures
          </h2>
          <p className="hero-subtitle">
            Connect directly with rural producers and discover authentic, handcrafted products from local artisans and farmers.
          </p>
          <div className="hero-categories">
            {categories.slice(1).map((category) => {
              const Icon = category.icon;
              return (
                <button
                  key={category.id}
                  onClick={() => setSelectedCategory(category.id)}
                  className="category-button"
                >
                  <Icon className="category-icon" />
                  <span>{category.name}</span>
                </button>
              );
            })}
          </div>
        </div>
      </div>
    </section>
  );
};

export default HeroSection;