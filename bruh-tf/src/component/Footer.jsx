import { MapPin, MessageCircle, Mail, Phone } from 'lucide-react';

function Footer() {
  return (
    <footer className="bg-gray-900 text-black">
      <div className='max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12'>
        <div className='grid grid-cols-1 md:grid-cols-4 gap-8'>
          <div className='md:col-span-2'>
            <div className='flex items-center space-x-3 mb-4'>
              <div className='w-8 h-8 bg-emerald-600 rounded-lg flex items-center justify-center'>
                <MapPin className="w-5 h-5 text-black"></MapPin>
              </div>
              <span className="text-xl font-bold">Local Market</span>
            </div>
            <p className="text-black-400 mb-4 max-w-md">
              Connecting rural producers with customers worldwide. Supporting local communities through direct trade and empowering artisans with technology.
            </p>
            <div>
              <div className='flex items-center space-x-2 text-gray-400'>
                <Mail className="w-4 h-4"/>
                <span className="text-sm">info@localmarket.com</span>
              </div>
              <div className="flex items-center space-x-2 text-gray-400">
                <Phone className='w-4 h-4'/>
                <span>+(855) 123-4567</span>
              </div>
            </div>
          </div>

          <div>
            <h3 className='font-semibold mb-4'>Categories</h3>
            <ul className="space-y-2 text-gray-400">
              <li><a href="#" className="hover:text-white transition-colors">Food & Beverages</a></li>
              <li><a href="#" className="hover:text-white transition-colors">Clothing</a></li>
              <li><a href="#" className="hover:text-white transition-colors">Gifts & Crafts</a></li>
              <li><a href="#" className="hover:text-white transition-colors">All Products</a></li>
            </ul>
          </div>

          <div>
            <h3 className="font-semibold mb-4">For Sellers</h3>
            <p className="text-gray-400 mb-4 text-sm">
              Want to sell your products? Join our community of rural producers.
            </p>
            <button className="bg-emerald-600 hover:bg-emerald-700 text-black px-4 py-2 rounded-lg font-medium transition-colors flex items-center space-x-2">
              <MessageCircle className="w-4 h-4" />
              <span>Join as Seller</span>
            </button>
          </div>
        </div>

        <div className="border-t border-gray-800 mt-8 pt-8 text-center text-gray-400">
          <p>&copy; 2025 RuralMarket. Supporting local producers worldwide.</p>
        </div>
      </div>
    </footer>
  );
}
export default Footer