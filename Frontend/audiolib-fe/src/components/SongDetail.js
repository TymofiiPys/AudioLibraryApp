import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { apiRequest } from "../utils/apiHelper";

const SongDetail = () => {
  const { songId } = useParams(); // Get the song ID from the URL params
  const [song, setSong] = useState(null);
  const [feedbacks, setFeedbacks] = useState([]);
  const [carriers, setCarriers] = useState([]);
  const [isRenting, setIsRenting] = useState(null); // Store which carrier is being rented
  const [rentEndDate, setRentEndDate] = useState(""); // Store the end date for rent
  const userId = localStorage.getItem("userId"); // Store user's id, assume it's available after login

  // Fetch song details, feedbacks, and carriers when the component mounts
  useEffect(() => {
    const fetchSongDetails = async () => {
      try {
        const data = await apiRequest(`/songs/${songId}`, "GET");
        
        // Extract data from the response
        setSong(data.songView);
        setFeedbacks(data.feedbacks);
        setCarriers(data.carriers);
      } catch (error) {
        console.error("Error fetching song details:", error);
      }
    };

    fetchSongDetails();
  }, [songId]);

  // Handle renting a song
  const handleRentSubmit = async (carrierId) => {
    if (!userId) {
      alert("User must be logged in to rent.");
      return;
    }

    const rentData = {
      userId: userId,              // User ID (from login or session)
      audioCarrierId: carrierId,   // Carrier ID
      dateEndOfRent: rentEndDate   // End date for the rent
    };

    try {
      await apiRequest("/rent", "POST", rentData); // Assuming '/rent' is the endpoint
      alert("Rent successfully created!");
      setIsRenting(null); // Close the rent form
      setRentEndDate(""); // Reset the end date
    } catch (error) {
      console.error("Error creating rent:", error);
      alert("Failed to create rent.");
    }
  };

  const renderRentForm = (carrierId) => (
    <div>
      <input
        type="date"
        value={rentEndDate}
        onChange={(e) => setRentEndDate(e.target.value)}
        required
      />
      <button onClick={() => handleRentSubmit(carrierId)}>Submit Rent</button>
    </div>
  );

  // Format duration (for example)
  const formatDuration = (duration) => {
    if (!duration || !duration.value) return "Duration not available";

    const { hours, minutes, seconds } = duration;
    const totalHours = hours + (duration.days * 24); // Convert days to hours if needed

    const parts = [];
    if (totalHours > 0) parts.push(`${totalHours} hours`);
    if (minutes > 0) parts.push(`${minutes} minutes`);
    if (seconds > 0) parts.push(`${Math.floor(seconds)} seconds`);

    return parts.join(" ");
  };

  if (!song) {
    return <p>Loading song details...</p>; // Loading state
  }

  return (
    <div className="song-detail">
      <h2>{song.name}</h2>
      <p><strong>Artist:</strong> {song.artist}</p>
      <p><strong>Album:</strong> {song.album}</p>
      <p><strong>Year:</strong> {song.year}</p>
      <p><strong>Genre:</strong> {song.genre}</p>
      <p><strong>Duration:</strong> {formatDuration(song.duration)}</p> {/* Formatted duration */}

      <h3>Carriers</h3>
      <ul>
        {carriers.map((carrier) => (
          <li key={carrier.id}>
            <p><strong>Carrier Type:</strong> {carrier.carrier}</p>
            <p><strong>Amount Available:</strong> {carrier.amtAvailable ?? "Digital"}</p>
            <button onClick={() => setIsRenting(carrier.id)}>Rent</button>

            {isRenting === carrier.id && renderRentForm(carrier.id)} {/* Show the rent form */}
          </li>
        ))}
      </ul>

      <h3>Feedbacks</h3>
      <ul>
        {feedbacks.map((feedback) => (
          <li key={feedback.id}>
            <p><strong>User {feedback.userId}:</strong></p>
            <p>Mark: {feedback.mark}</p>
            <p>{feedback.text}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SongDetail;
