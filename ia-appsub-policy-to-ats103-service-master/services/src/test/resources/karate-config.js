
function() {
    var env = karate.properties['test.env']

    karate.configure('logPrettyRequest', true)
    karate.configure('logPrettyResponse', true)
    karate.configure('connectTimeout', 30 * 1000)
    karate.configure('readTimeout', 30 * 1000)

    var config = {
        env: env,
        data: read("classpath:data.json")[env],
        global: read("classpath:global.json")[env]
    };

    for (p in config) {
        karate.log ("##### config: " + p, ": " + config[p])
    }
    return config;
}
