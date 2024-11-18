import React from "react";
import { useNavigate } from "react-router-dom";

const MainMenu = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Clear user data (e.g., userId from localStorage)
    localStorage.removeItem("userId");

    // Redirect to login page
    navigate("/login");
  };

  return (
    <div className="main-menu">
      <h2>Main Menu</h2>
      <div>
        <button onClick={() => navigate("/choose-song")}>Choose Song</button>
      </div>
      <div>
        <button onClick={() => navigate("/choose-book")}>Choose Book</button>
      </div>
      <div>
        <button onClick={() => navigate("/your-rents")}>Your Rents</button>
      </div>
      <div>
        <button onClick={handleLogout}>Logout</button>
      </div>
    </div>
  );
};

export default MainMenu;
