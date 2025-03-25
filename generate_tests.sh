SYMBOL=BTC-USDT
START_DATE=`date --date="3 months ago" +"%s"`
END_DATE=`date +"%s"`
echo '"TIMESTAMP", "OPEN", "CLOSE", "HIGH", "LOW", "VOLUME", "QUOTE_VOLUME", "CURRENCY_PAIR"' > src/test/resources/candles-for-backtesting-btc-usdt.csv
curl -s "https://api.kucoin.com/api/v1/market/candles?type=15min&symbol=${SYMBOL}&startAt=${START_DATE}&endAt=${END_DATE}" \
| jq --arg SYMBOL "$SYMBOL" -r -c '.data[] | . + [$SYMBOL] | @csv' \
| tac $1 >> src/test/resources/candles-for-backtesting-btc-usdt.csv