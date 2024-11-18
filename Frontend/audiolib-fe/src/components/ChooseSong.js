import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { apiRequest } from "../utils/apiHelper";

const ChooseSong = () => {
  const [songs, setSongs] = useState([]);
  const [search, setSearch] = useState("");
  const navigate = useNavigate();

  // Fetch songs when the component mounts or search changes
  useEffect(() => {
    const fetchSongs = async () => {
      try {
        const searchParams = new URLSearchParams();
        if (search) {
          searchParams.append("name", search);
        }

        const data = await apiRequest(`/songs?${searchParams.toString()}`, "GET");
        setSongs(data); // Set the fetched songs
      } catch (error) {
        console.error("Error fetching songs:", error);
      }
    };

    fetchSongs();
  }, [search]); // Re-run the effect when search term changes

  const handleSearchChange = (e) => {
    setSearch(e.target.value); // Update search term
  };

  const handleSongClick = (songId) => {
    navigate(`/song/${songId}`); // Redirect to the song's detail page
  };

  return (
    <div className="choose-song">
      <h2>Choose Song</h2>
      <div>
        <input
          type="text"
          placeholder="Search by name"
          value={search}
          onChange={handleSearchChange}
        />
      </div>
      <ul>
        {songs.map((song) => (
          <li key={song.id} onClick={() => handleSongClick(song.id)}>
            <h3>{song.name}</h3>
            <p>{song.artist}</p>
            <p>{song.album}</p>
            <p>{song.year}</p>
            <p>{song.genre}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ChooseSong;
