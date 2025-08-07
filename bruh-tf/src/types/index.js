export const CATEGORIES = {
  ALL: 'all',
  FOOD_BEV: 'food-bev',
  CLOTHING: 'clothing',
  GIFTS: 'gifts'
};

export const createProduct = (id, name, price, description, image, category, seller, featured = false) => ({
  id,
  name,
  price,
  description,
  image,
  category,
  seller,
  featured
});

export const createSeller = (name, location, telegram, rating) => ({
  name,
  location,
  telegram,
  rating
});