import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { apiRequest } from "../utils/apiHelper";

const BookDetail = () => {
  const { bookId } = useParams(); // Get the book ID from the URL params
  const [book, setBook] = useState(null);
  const [feedbacks, setFeedbacks] = useState([]);
  const [carriers, setCarriers] = useState([]);

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

// Format duration to display only hours, minutes, and seconds
const formatDuration = (duration) => {
    if (!duration || !duration.value) return "Duration not available";
  
    const { hours, minutes, seconds } = duration;
  
    // If the audio exceeds 24 hours, show the total number of hours
    const totalHours = hours + (duration.days * 24); // Convert days to hours if needed
  
    // Build the duration string
    const parts = [];
    if (totalHours > 0) parts.push(`${totalHours} hours`);
    if (minutes > 0) parts.push(`${minutes} minutes`);
    if (seconds > 0) parts.push(`${Math.floor(seconds)} seconds`);
  
    return parts.join(" ");
  };
  

export default BookDetail;
