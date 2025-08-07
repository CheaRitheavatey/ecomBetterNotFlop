import { Search, Filter, MapPin } from "lucide-react";

function Header({searchTerm, setSearchTerm, showFilter, setShowFilter}) {
  return (
    <>
        {/* main header */}
      <header className="bg-white shadow-sm border-b border-gray-200">
        <div>
          <div>
            <div>
              <div className="w-8 h-8 bg-gradient-to-br from-emerald-600 to-emerald-700 rounded-lg flex items-center justify-center">
                <MapPin className="w-5 h-5 text-white" />
              </div>
              <div>
                <h1 className="text-xl font-bold text-gray-900">Local Market</h1>
                <p className="text-xs text-gray-500">Supporting Local Producers</p>
              </div>
            </div>

            <div className="hidden md:flex items-center space-x-6">
              <div className="relative">
                <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4"></Search>
                <input 
                type="text"
                placeholder="Search Products..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-emerald-500 focus:border-emerald-500 w-64" 
                />
              </div>
              <button
                onClick={() => setShowFilter(!showFilter)}
                className="flex items-center space-x-2 px-4 py-2 text-gray-700 hover:text-emerald-600 transition-colors"
              >
                <Filter className="w-4 h-4"></Filter>
                <span>Filters</span>
              </button>
            </div>
          </div>
        </div>
      </header>
    

      {/* mobile search */}
      <div className="md:hidden bg-white border-b border-gray-200 p-4">
        <div className="relative">
          <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
          <input
            type="text"
            placeholder="Search products..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-emerald-500 focus:border-emerald-500"
          />
        </div>
      </div>
    </>
    
  );
}
export default Header