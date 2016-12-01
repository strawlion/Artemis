import './PersonFilter.css';
import React from 'react';
import { connect } from 'react-redux'
import { Card, Chip } from 'react-materialize';
import * as d3 from 'd3';
import * as chromatic from 'd3-scale-chromatic';



import actions from '../../state/actions';
import { genderConceptIdToGender } from '../../utils/enums';



const colorScale = d3.scaleOrdinal(chromatic.schemePastel1);
export default connect(mapStateToProps, mapDispatchToProps)(PersonFilter)

function PersonFilter({ onFilterChanged }) {


    const filterCategories = [
        {
            displayName: 'Gender',
            options: getEnumValues(genderConceptIdToGender),
        }
    ];

    return (
        <Card className="person-filter">
            {filterCategories.map(toFilterCategories)}
        </Card>
  );

    function toFilterCategories(category) {
        return (
            <div key={category.displayName} className="filter-category">
                <span>{category.displayName}</span>
                {category.options.map(toFilterOptions)}
            </div>
        );

        function toFilterOptions(option, index) {
            return (
                <span
                    key={option.id}
                    className="filter-option"
                    style={{ backgroundColor: colorScale(index) }}
                    onClick={ () => onFilterChanged(category, option.id) }>{option.displayName}</span>
            );
        }
    }





  // Gender
            // <Chip>Male></Chip>
}

function mapStateToProps(state) {
  return {
      //searchText: state.searchText,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    onFilterChanged,
  };

  function onFilterChanged(category, value) {
      dispatch(actions.filterChanged(category, value));
  }
}


function getEnumValues(enumeration) {
    return Object.keys(enumeration).map(toValue);

    function toValue(id) {
        return {
            id,
            displayName: enumeration[id],
        };
    }
}