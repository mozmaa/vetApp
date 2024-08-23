import PropTypes from "prop-types";


function NavIcons ( { wrapperClass, iconClass, tooltipClass, tooltipLabel} ) {
    return (
        <>
            <div className={wrapperClass}>
            <i className={iconClass}></i>
            <div className={tooltipClass}>{tooltipLabel}</div>
            </div>
        </>
    )
}

NavIcons.propTypes = {
    wrapperClass: PropTypes.string.isRequired,
    iconClass: PropTypes.string.isRequired,
    tooltipClass: PropTypes.string.isRequired,
    tooltipLabel: PropTypes.string.isRequired
  };

export default NavIcons