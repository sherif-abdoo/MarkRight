import React, { useState, useRef } from 'react';
import './GlossyCard.css';

const GlowCard = ({ title, content, glowColor = '#2b96e2' }) => {
    const [position, setPosition] = useState({ x: 0, y: 0 });
    const [isHovering, setIsHovering] = useState(false);
    const cardRef = useRef(null);

    // Function to convert hex to rgb
    const hexToRgb = (hex) => {
        const result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result
            ? `${parseInt(result[1], 16)}, ${parseInt(result[2], 16)}, ${parseInt(result[3], 16)}`
            : "138, 43, 226"; // Default purple if invalid
    };

    const rgbValue = hexToRgb(glowColor);

    const handleMouseMove = (e) => {
        if (cardRef.current) {
            const rect = cardRef.current.getBoundingClientRect();
            setPosition({
                x: e.clientX - rect.left,
                y: e.clientY - rect.top
            });
        }
    };

    const handleMouseEnter = () => {
        setIsHovering(true);
    };

    const handleMouseLeave = () => {
        setIsHovering(false);
    };

    return (
        <div
            className="glow-card"
            style={{ cursor: 'pointer' }}
            ref={cardRef}
            onMouseMove={handleMouseMove}
            onMouseEnter={handleMouseEnter}
            onMouseLeave={handleMouseLeave}
        >
            <div
                className="glow-effect"
                style={{
                    background: `radial-gradient(
            circle at ${position.x}px ${position.y}px, 
            rgba(${rgbValue}, ${isHovering ? 0.6 : 0.15}), 
            transparent 60%
          )`
                }}
            ></div>

            <div
                className="border-effect"
                style={{
                    border: `1px solid rgba(${rgbValue}, ${isHovering ? 0.4 : 0.1})`,
                    boxShadow: isHovering
                        ? `0 0 15px rgba(${rgbValue}, 0.25), inset 0 0 15px rgba(${rgbValue}, 0.1)`
                        : `0 0 8px rgba(${rgbValue}, 0.1), inset 0 0 8px rgba(${rgbValue}, 0.03)`
                }}
            ></div>

            <div className="glow-card-content">
                <div className="star-icon" style={{ color: `rgba(${rgbValue}, 0.9)` }}>★</div>
                <h2>{title || 'Add Your Heading Text Here'}</h2>
                <p>{content || 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut elit tellus, luctus nec ullamcorper mattis, pulvinar dapibus leo.'}</p>
            </div>
        </div>
    );
};

export default GlowCard;