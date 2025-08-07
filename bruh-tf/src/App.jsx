import CategoryNavigation from "./component/CategoryNavigation";
import Footer from "./component/Footer";
import Header from "./component/Header";

function App() {
  return (
    <div>
      <Header/>
      {/* <h1>This is header</h1> */}
      <CategoryNavigation></CategoryNavigation>
      <Footer />
    </div>
  );
}

export default App;
