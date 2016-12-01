export default {
    getPersons,
    getPersonById,
};


async function getPersons() {
    return GET('/person');
}

async function getPersonById(personId) {
    // TODO: Remove person here
    const [person, death, observations, observationPeriods] = await Promise.all([
        GET(`/person/${personId}`),
        GET(`/death/${personId}`),
        GET(`/observation/${personId}`),
        GET(`/observation-period/${personId}`)
    ]);

    return {
        person,
        death,
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