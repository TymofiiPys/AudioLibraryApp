import React, { useState, useEffect } from "react";
import { apiRequest } from "../utils/apiHelper";

const AudioRentStats = () => {
  const [songs, setSongs] = useState([]);
  const [books, setBooks] = useState([]);
  const [selectedAudio, setSelectedAudio] = useState(null);
  const [rentStats, setRentStats] = useState(null);
  const [error, setError] = useState(null);
  const [dialogOpen, setDialogOpen] = useState(false);

  useEffect(() => {
    // Fetch songs and books on load
    const fetchData = async () => {
      try {
        const songsResponse = await apiRequest("/songs", "GET");
        const booksResponse = await apiRequest("/books", "GET");
        setSongs(songsResponse);
        setBooks(booksResponse);
      } catch (err) {
        setError("Error fetching songs or books.");
        console.error(err);
      }
    };

    fetchData();
  }, []);

  const handleAudioClick = async (id) => {
    try {
      const response = await apiRequest(`/rent_stat/${id}`, "GET");
      setRentStats(response); // Set rent stats for the selected audio
      setDialogOpen(true); // Open dialog
    } catch (err) {
      setError("Error fetching rent statistics.");
      console.error(err);
    }
  };

  const closeDialog = () => {
    setDialogOpen(false);
    setRentStats(null);
  };

  return (
    <div>
      <h1>Audio Rent Stats</h1>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <div>
        <h2>Songs</h2>
        {songs.length > 0 ? (
          <ul>
            {songs.map((song) => (
              <li key={song.id} onClick={() => handleAudioClick(song.id)}>
                {song.name} by {song.artist}
              </li>
            ))}
          </ul>
        ) : (
          <p>No songs available.</p>
        )}
      </div>

      <div>
        <h2>Books</h2>
        {books.length > 0 ? (
          <ul>
            {books.map((book) => (
              <li key={book.id} onClick={() => handleAudioClick(book.id)}>
                {book.name} by {book.author}
              </li>
            ))}
          </ul>
        ) : (
          <p>No books available.</p>
        )}
      </div>

      {dialogOpen && rentStats && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={closeDialog}>
              &times;
            </span>
            <h2>Rent Stats</h2>
            <p>
              <strong>ID:</strong> {rentStats.audioId}
            </p>
            <p>
              <strong>Name:</strong> {rentStats.name}
            </p>
            <p>
              <strong>Creator:</strong> {rentStats.creator}
            </p>
            <p>
              <strong>Average Rent (days):</strong> {rentStats.avgrent}
            </p>
            <p>
              <strong>Total Rents:</strong> {rentStats.rents}
            </p>
          </div>
        </div>
      )}

      {/* Styles for modal */}
      <style>{`
        .modal {
          position: fixed;
          top: 0;
          left: 0;
          width: 100%;
          height: 100%;
          background-color: rgba(0, 0, 0, 0.5);
          display: flex;
          justify-content: center;
          align-items: center;
        }
        .modal-content {
          background-color: white;
          padding: 20px;
          border-radius: 8px;
          box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
          width: 400px;
        }
        .close {
          position: absolute;
          top: 10px;
          right: 10px;
          font-size: 20px;
          cursor: pointer;
        }
      `}</style>
    </div>
  );
};

export default AudioRentStats;
