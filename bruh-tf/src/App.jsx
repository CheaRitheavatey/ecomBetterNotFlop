import CategoryNavigation from "./component/CategoryNavigation";
import Footer from "./component/Footer";
import Header from "./component/Header";
import HeroSection from "./component/HeroSection";

function App() {
  return (
    <div>
      <Header/>
      <CategoryNavigation></CategoryNavigation>
      <HeroSection/>
      <Footer />
    </div>
  );
}
export default App;
