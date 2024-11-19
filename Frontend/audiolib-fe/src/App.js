import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import LoginPage from "./components/auth/Login";
import MainMenu from "./components/MainMenu";
import SongDetail from "./components/SongDetail";
import BookDetail from "./components/BookDetail";
import ChooseSong from "./components/ChooseSong";  // Import the ChooseSong component
import ChooseBooks from "./components/ChooseBook"; // Import the ChooseBooks component
import { apiRequest } from "./utils/apiHelper";
import Inventory from "./components/Inventory";
import Sales from "./components/Sales";
import PopularSongs from "./components/PopularSongs";
import PopularBooks from "./components/PopularBooks";
import AudioRentStats from "./components/AudioRentStats";

const App = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false); // User authentication status
  const [userId, setUserId] = useState(null); // Store user ID after successful login

  // Check if user is authenticated when the app loads
  useEffect(() => {
    const storedUserId = localStorage.getItem("userId");
    if (storedUserId) {
      setUserId(storedUserId);
      setIsAuthenticated(true);
    }
  }, []);

  // Login handler: After login, set the user ID and authentication status
  const handleLogin = async (email, password) => {
    try {
      const response = await apiRequest("/login", "POST", { email, password });
      if (response.userId) {
        localStorage.setItem("userId", response.userId); // Save user ID in local storage
        setUserId(response.userId);
        setIsAuthenticated(true);
      }
    } catch (error) {
      console.error("Login failed:", error);
    }
  };

  return (
    <Router>
      <Routes>
        {/* Redirect from root to login */}
        <Route path="/" element={<Navigate to="/login" />} />

        {/* Public Routes */}
        <Route path="/login" element={<LoginPage onLogin={handleLogin} />} />

        {/* Protected Routes */}
        <Route
          path="/main-menu"
          element={isAuthenticated ? <MainMenu /> : <Navigate to="/login" />}
        />
        <Route
          path="/song/:songId"
          element={isAuthenticated ? <SongDetail /> : <Navigate to="/login" />}
        />
        <Route
          path="/book/:bookId"
          element={isAuthenticated ? <BookDetail /> : <Navigate to="/login" />}
        />

        {/* ChooseSong and ChooseBooks Routes */}
        <Route
          path="/choose-song"
          element={isAuthenticated ? <ChooseSong /> : <Navigate to="/login" />}
        />
        <Route
          path="/choose-book"
          element={isAuthenticated ? <ChooseBooks /> : <Navigate to="/login" />}
        />
        <Route
          path="/inventory"
          element={isAuthenticated ? <Inventory /> : <Navigate to="/login" />}
        />
        <Route
          path="/sales"
          element={isAuthenticated ? <Sales /> : <Navigate to="/login" />}
        />
        <Route
          path="/popular-songs"
          element={isAuthenticated ? <PopularSongs /> : <Navigate to="/login" />}
        />
        <Route
          path="/popular-books"
          element={isAuthenticated ? <PopularBooks /> : <Navigate to="/login" />}
        />
        <Route
          path="/audio-rent-stats"
          element={isAuthenticated ? <AudioRentStats /> : <Navigate to="/login" />}
        />
      </Routes>
    </Router>
  );
};

export default App;
