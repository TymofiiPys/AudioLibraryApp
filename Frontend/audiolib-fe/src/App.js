import { useAuth0 } from "@auth0/auth0-react";
import "./App.css";
import { Router, Route, Routes } from "react-router-dom";
import LoginButton from "./auth/login";
import LogoutButton from "./auth/logout";
import Profile from "./profile/profile";

function App() {
  const { isAuthenticated } = useAuth0();
  const LogInOutBtn = isAuthenticated ? LogoutButton : LoginButton;
  return (
    <Router>
      <div>
        Привіт! я чисто тестую логін. завтра зроблю більше фронта. на кнопочку
        <LogInOutBtn />
        <Routes>
          <Route path="/profile" element={Profile}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
