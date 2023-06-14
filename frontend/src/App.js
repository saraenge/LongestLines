import './App.css';
import {useEffect, useState} from "react";
import LineRow from "./LineRow";

function App() {
  const [lines, setLines] = useState([])

  const BASE_URL = 'http://localhost:8080/api/longestLines'
  const linesUrl = BASE_URL + '/lines';

  const fetchLines = () => {
    return fetch(linesUrl)
        .then((res) => res.json())
        .then((l) => setLines(l))
        .catch((error) => console.log('Error fetching lines:', error));
  }

  useEffect(() => {
    fetchLines();
  }, [])

  return (
      <div className="App">
        <header className="App-header">
          <h1>Longest lines</h1>
          {lines?.map((line, arrayIndex) => (
              <LineRow
                  key={arrayIndex}
                  line={line.lineNumber}
                  direction={line.direction}
                  numberOfStops={line.numberOfStops}
              />
              ))}
        </header>
      </div>
  );
}

export default App;
