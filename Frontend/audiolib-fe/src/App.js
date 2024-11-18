import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import MainMenu from "./components/MainMenu"; // Import MainMenu
import Login from "./components/auth/Login"; // Import Login
import Register from "./components/auth/Register"; // Import Register
import ChooseSong from "./components/ChooseSong"; // Placeholder for future page
import ChooseBook from "./components/ChooseBook"; // Placeholder for future page
import YourRents from "./components/YourRents"; // Placeholder for future page

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />  {/* Add the Register route */}
        <Route path="/main-menu" element={<MainMenu />} />
        <Route path="/choose-song" element={<ChooseSong />} />
        <Route path="/choose-book" element={<ChooseBook />} />
        <Route path="/your-rents" element={<YourRents />} />
      </Routes>
    </Router>
  );
};

export default App;
