export default {
    getPersons,
    getPersonById,
};


async function getPersons(filters, page) {
    const filterSegments = filters ? Object.keys(filters).map(categoryId => categoryId + '=' + filters[categoryId]) : [];
    const pageSegment = page != null ? 'page=' + page : null;
    const queryString = '?' + [...filterSegments, pageSegment].join('&');
    return GET(`/person${queryString}`);
}

async function getPersonById(personId) {
    // TODO: Remove person here
    const [person, observations, observationPeriods] = await Promise.all([
        GET(`/person/${personId}`),
        GET(`/observation/${personId}`),
        GET(`/observation-period/${personId}`)
    ]);

    return {
        person,
        observations,
        observationPeriods,
    };
}


async function GET(url) {
    const response = await window.fetch(`http://localhost:8888${url}`);
    // return JSON.parse(response._bodyText)
    let json = null;
    try {
        json = await response.json();
    }
    catch (exception) {}

    return json;
}