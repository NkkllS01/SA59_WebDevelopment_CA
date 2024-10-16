import "./App.css";
import Header from "./header";
import Footer from "./footer";

function App() {
  const cssURL = "http://localhost:8080/css/custom-style-jm.css";
  return (
      <div className="d-flex flex-column min-vh-100">
        <link rel="stylesheet" href={cssURL} />
        <Header />
        <div className="flex-grow-1">
        </div>
        <Footer />
      </div>
  );
}

export default App;
