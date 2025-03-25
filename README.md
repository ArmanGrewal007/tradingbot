<div align="center"><h1>TradingBot</h1> <br> <img src="https://media1.tenor.com/m/YlBfgZ3_INcAAAAd/cat-kitty.gif" /></div>

Initialized using 
```bash
brew install maven
# Run the project starter
mvn archetype:generate -B \
-DarchetypeGroupId=tech.cassandre.trading.bot \
-DarchetypeArtifactId=cassandre-trading-bot-spring-boot-starter-basic-archetype \
-DarchetypeVersion=6.0.1 \
-DgroupId=com.mycompany.bot \
-DartifactId=my-trading-bot \
-Dversion=1.0-SNAPSHOT \
-Dpackage=com.mycompany.bot
# More changes in the project ...
mvn test # to test how it performs (will fail the testcase)
```