import { categories } from '../data/categories.js';

const CategoryNavigation = ({ selectedCategory, setSelectedCategory }) => {
  return (
    <section className="category-navigation-container">
      <div className="category-buttons-container">
        {categories.map((category) => {
          const Icon = category.icon;
          return (
            <button
              key={category.id}
              onClick={() => setSelectedCategory(category.id)}
              className={`category-button ${selectedCategory === category.id ? 'active' : ''}`}
            >
              <Icon className="category-icon" />
              <span>{category.name}</span>
            </button>
          );
        })}
      </div>
    </section>
  );
};

export default CategoryNavigation;