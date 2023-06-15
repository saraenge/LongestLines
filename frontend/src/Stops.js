import './Stops.css';

const Stops = ({stops}) => {
    return (
        <ul className="List">
            {stops?.map((stop, arrayIndex) => (
                <li key={arrayIndex} className="text">{stop}</li>
            ))}
        </ul>

    );
};

export default Stops;