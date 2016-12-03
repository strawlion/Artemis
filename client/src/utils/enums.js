import api from './api';

const genderConceptIdToGender = {
  8532: 'Female',
  8507: 'Male',
};

// The way it works is this: The race field contains races and ethnic backgrounds, while for Ethnicity there are only two categories for data on ethnicity: “Hispanic or Latino” (concept_id=38003563) and “Not Hispanic or Latino” (concept_id=38003564). This means, the two categories are orthogonal to each other, and both Latinos and non-Latinos can have any racial or ethnic background.
const ethnicityConceptIdToEthnicity = {
    38003563: 'Hispanic or Latino',
    38003564: 'Not Hispanic or Latino',
};

const raceConceptIdToEthnicity = {
    8515: 'Asian',
    8516: 'Black',
    8522: 'Other/Unknown/Multiracial',
    8527: 'White',
    8552: 'Undetermined',
    8557: 'Pacific Islander',
    8558: 'Hispanic',
    8657: 'American Indian/Alaska Native',
    9178: 'Non-White',
};

export {
    genderConceptIdToGender,
    ethnicityConceptIdToEthnicity,
    raceConceptIdToEthnicity,
};