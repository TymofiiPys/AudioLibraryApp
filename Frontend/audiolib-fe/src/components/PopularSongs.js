import React, { useState } from "react";
import { apiRequest } from "../utils/apiHelper"; // Assuming this is where your API helper is

const PopularSongs = () => {
  const [amt, setAmt] = useState(5); // Default to 5 entries
  const [songsData, setSongsData] = useState([]);
  const [error, setError] = useState(null);

  const handleAmtChange = (e) => {
    setAmt(e.target.value);
  };

  const handleFetchPopularSongs = async () => {
    if (amt <= 0) {
      setError("Please specify a valid number of entries.");
      return;
    }

    setError(null); // Clear previous errors
    
    try {
      const response = await apiRequest(`/top_songs?top=${amt}`, "GET");
      setSongsData(response); // Assume response is an array of popular songs
    } catch (err) {
      setError("Error fetching popular songs.");
      console.error(err);
    }
  };

  return (
    <div>
      <h1>Most Popular Songs</h1>

      <div>
        <label htmlFor="amt">Number of Entries: </label>
        <input
          type="number"
          id="amt"
          value={amt}
          onChange={handleAmtChange}
          min="1"
        />
        <button onClick={handleFetchPopularSongs}>Fetch Popular Songs</button>
      </div>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {songsData.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Song Name</th>
              <th>Artist</th>
              <th>Listened</th>
            </tr>
          </thead>
          <tbody>
            {songsData.map((song) => (
              <tr key={song.id}>
                <td>{song.id}</td>
                <td>{song.name}</td>
                <td>{song.artist}</td>
                <td>{song.listened}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default PopularSongs;
