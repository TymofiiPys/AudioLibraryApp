import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const MainMenu = () => {
  const navigate = useNavigate();
useEffect(() => console.log(localStorage.getItem("userId")))
  const handleLogout = () => {
    // Clear user data (e.g., userId from localStorage)
    localStorage.removeItem("userId");

    // Redirect to login page
    navigate("/login");
  };
  const userId = localStorage.getItem("userId");
  const isAdmin = userId === "PYSARENKOV";

  return (
    <div>
      <h1>Main Menu</h1>

      {/* General Menu Items */}
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
        <button onClick={() => navigate("/logout")}>Logout</button>
      </div>

      {/* Admin-only buttons */}
      {isAdmin && (
        <div>
          <div>
            <button onClick={() => navigate("/inventory")}>Show Inventory</button>
          </div>
          <div>
            <button onClick={() => navigate("/sales")}>Show Sales</button>
          </div>
          <div>
            <button onClick={() => navigate("/most-popular-songs")}>Most Popular Songs</button>
          </div>
          <div>
            <button onClick={() => navigate("/most-popular-books")}>Most Popular Books</button>
          </div>
          <div>
            <button onClick={() => navigate("/audio-rent-stats")}>Audio Rent Stats</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default MainMenu;
