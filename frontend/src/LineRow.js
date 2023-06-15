import './LineRow.css';
import {useState} from "react";
import Stops from "./Stops";

const LineRow = ({line, direction, numberOfStops}) => {
    const [stops, setStops] = useState([]);
    const [showStops, setShowStops] = useState(false);

    const BASE_URL = 'http://localhost:8080/api/longestLines'
    const stopsUrl = (line, direction) => `${BASE_URL}/stops?line=${line}&direction=${direction}`;

    const toggleShowStops = () => {
        setShowStops(!showStops);
        if (stops.length == 0) {
            fetchStops();
        }
    }

    const fetchStops = () => {
        return fetch(stopsUrl(line, direction))
            .then((res) => res.json())
            .then((l) => setStops(l))
    }

    return (
        <div>
            <div className="Line-button-wrapper">
                <button
                    className="Line-button"
                    onClick={toggleShowStops}
                >
                    <div className="Line-info">Linje: {line}</div>
                    <div className="Line-info">Riktning: {direction}</div>
                    <div className="Line-info">Antal hållplatser: {numberOfStops}</div>
                </button>
            </div>
            {showStops && stops &&
                <Stops
                    stops={stops.stops}
                />
            }
        </div>
    );
};

export default LineRow;