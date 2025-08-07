import CategoryNavigation from "./component/CategoryNavigation";
import FeaturedProducts from "./component/FeaturedProducts";
import Footer from "./component/Footer";
import Header from "./component/Header";
import HeroSection from "./component/HeroSection";
import ProductCard from "./component/ProductCard";

function App() {
  return (
    <div>
      <FeaturedProducts></FeaturedProducts>
      <Header/>

      <HeroSection/>


      <CategoryNavigation></CategoryNavigation>
      <Footer />
    </div>
  );
}
export default App;
