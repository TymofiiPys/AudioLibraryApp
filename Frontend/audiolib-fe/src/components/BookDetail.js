import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { apiRequest } from "../utils/apiHelper";

const BookDetail = () => {
  const { bookId } = useParams(); // Get the book ID from the URL params
  const [book, setBook] = useState(null);
  const [feedbacks, setFeedbacks] = useState([]);
  const [carriers, setCarriers] = useState([]);
  const [isRenting, setIsRenting] = useState(null); // Store which carrier is being rented
  const [rentEndDate, setRentEndDate] = useState(""); // Store the end date for rent
  const userId = localStorage.getItem("userId"); // Store user's id, assume it's available after login

  // Fetch book details, feedbacks, and carriers when the component mounts
  useEffect(() => {
    const fetchBookDetails = async () => {
      try {
        const data = await apiRequest(`/books/${bookId}`, "GET");

        // Extract data from the response
        setBook(data.bookView);
        setFeedbacks(data.feedbacks);
        setCarriers(data.carriers);
      } catch (error) {
        console.error("Error fetching book details:", error);
      }
    };

    fetchBookDetails();
  }, [bookId]);

  // Handle renting a book
  const handleRentSubmit = async (carrierId) => {
    if (!userId) {
      alert("User must be logged in to rent.");
      return;
    }

    const rentData = {
      userId: userId,            // User ID (from login or session)
      bookCarrierId: carrierId,  // Carrier ID
      dateEndOfRent: rentEndDate // End date for the rent
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

  // Format duration (for example, if available)
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

  if (!book) {
    return <p>Loading book details...</p>; // Loading state
  }

  return (
    <div className="book-detail">
      <h2>{book.name}</h2>
      <p><strong>Author:</strong> {book.author}</p>
      <p><strong>Publisher:</strong> {book.publisher}</p>
      <p><strong>Year:</strong> {book.year}</p>
      <p><strong>Genre:</strong> {book.genre}</p>
      <p><strong>Duration:</strong> {formatDuration(book.duration)}</p> {/* Formatted duration */}

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

export default BookDetail;
