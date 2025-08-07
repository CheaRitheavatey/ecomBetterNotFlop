import { categories } from "../data/categories";

function HeroSection({setSelectedCategory}) {
    return (
        <section className="bg-gradient-to-r from-emerald-600 to-emerald-700 text-black">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
                <div className="text-center">
                    <h2 className="text-4xl md:text-5xl font-bold mb-4">
                        Discover Local Treasures
                    </h2>
                    <p className="text-xl text-emerald-100 mb-8 max-w-2xl mx-auto">
                        Connect directly with rural producers and discover authentic, handcrafted products from local artisans and farmers.
                    </p>
                    <div className="flex flex-wrap justify-center gap-3">
                        {categories.slice(1).map((category) => {
                            const Icon = category.icon;
                            return (
                                <button
                                    key={category.id}
                                    onClick={() => setSelectedCategory(category.id)}
                                    className="flex items-center space-x-2 bg-white/10 hover:bg-white/20 backdrop-blur-sm px-6 py-3 rounded-full transition-all duration-200 hover:scale-105"
                                >
                                    <Icon className="w-4 h-4" />
                                    <span>{category.name}</span>
                                </button>
                            );
                        })}
                    </div>
                </div>
            </div>
        </section>
    );
}
export default HeroSection