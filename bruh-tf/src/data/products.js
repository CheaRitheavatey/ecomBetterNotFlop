import { createProduct, CATEGORIES } from '../types/index.js';
import { sellers } from './sellers.js';

export const sampleProducts = [
  createProduct(
    '1',
    'Organic Honey',
    15,
    'Pure wildflower honey from local beekeepers. Rich flavor and natural sweetness.',
    '/images/image1.png',
    CATEGORIES.FOOD_BEV,
    sellers.maria,
    true
  ),
  createProduct(
    '2',
    'Handwoven Scarf',
    32,
    'Beautiful traditional scarf made with locally sourced wool. Unique patterns.',
    '/images/image2.png',
    CATEGORIES.CLOTHING,
    sellers.carlos,
    false
  ),
  createProduct(
    '3',
    'Ceramic Vase',
    45,
    'Hand-crafted ceramic vase with traditional glazing techniques. Perfect for home decor.',
    '/images/image3.png',
    CATEGORIES.GIFTS,
    sellers.ana,
    true
  ),
  createProduct(
    '4',
    'Fresh Coffee Beans',
    18,
    'Single-origin coffee beans grown in high altitude. Medium roast with chocolate notes.',
    '/images/image4.png',
    CATEGORIES.FOOD_BEV,
    sellers.miguel,
    false
  ),
  createProduct(
    '5',
    'Embroidered Blouse',
    28,
    'Traditional embroidered blouse with colorful floral patterns. Comfortable cotton fabric.',
    '/images/image5.png',
    CATEGORIES.CLOTHING,
    sellers.rosa,
    false
  ),
  createProduct(
    '6',
    'Wooden Jewelry Box',
    35,
    'Handcrafted wooden jewelry box with intricate carvings. Made from sustainable wood.',
    '/images/image6.png',
    CATEGORIES.GIFTS,
    sellers.pedro,
    true
  ),
  createProduct(
    '7',
    'Artisan Bread',
    8,
    'Fresh baked sourdough bread made with traditional methods and local grains.',
    '/images/image7.png',
    CATEGORIES.FOOD_BEV,
    sellers.maria,
    false
  ),
  createProduct(
    '8',
    'Knitted Sweater',
    55,
    'Warm wool sweater with traditional patterns. Hand-knitted with care.',
    '/images/image8.png',
    CATEGORIES.CLOTHING,
    sellers.carlos,
    false
  )
];