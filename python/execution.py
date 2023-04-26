import json
import re

import pandas as pd

tickerdata = []
executiondata = []
orderbookdata = []

# Regular expression pattern to match JSON data within a line
matchJason = re.compile(r'{.*}')
matchExchangePair = re.compile(r'Exchange=(.*?)::Pair=(.*?)::Type=(.*?)::')
matchExecutions = re.compile(r'\"success\":true')

with open('./logs/Testslf4j-20230422.log', 'r') as f:
    for line in f:
        foundJson = matchJason.search(line)
        if foundJson:
            json_data = foundJson.group()
            otherMatches = matchExchangePair.search(line)
            if otherMatches:
                xData = json.loads(json_data)
                xData['Exchange'] = otherMatches.group(1)
                xData['CoinPair'] = otherMatches.group(2)
                xData['Type'] = otherMatches.group(3)
                # json_data = json.dumps(xData)
                
                if (otherMatches.group(3) == "ticker"):
                    tickerdata.append(xData)
                    
                ## There are duplicated execution in different round fetch
                elif (otherMatches.group(3) == "execution"):
                    if matchExecutions.search(line) :
                        for xFill in (list)(xData['data']) :
                            executiondata.append(xFill)
                else :
                    for xAsk in (list)(xData['asks']) :
                        xOrder = {}
                        xOrder['Side'] = 'ask'
                        xOrder['Exchange'] = xData['Exchange']
                        xOrder['CoinPair'] = xData['CoinPair']
                        xOrder['Type'] = xData['Type']
                        xOrder['Price'] = xAsk[0]
                        xOrder['Qty'] = xAsk[1]
                        orderbookdata.append(xOrder)
                    
                    for xAsk in (list)(xData['bids']) :
                        xOrder = {}
                        xOrder['Side'] = 'bid'
                        xOrder['Exchange'] = xData['Exchange']
                        xOrder['CoinPair'] = xData['CoinPair']
                        xOrder['Type'] = xData['Type']
                        xOrder['Price'] = xAsk[0]
                        xOrder['Qty'] = xAsk[1]
                        orderbookdata.append(xOrder)
                                        
                # print(data)
        else :
            print( "ignore " + line)
            
    tickDataF = pd.DataFrame(tickerdata)
    executionDataF = pd.DataFrame(executiondata)
    orderbookDataF = pd.DataFrame(orderbookdata)
    
    print(tickDataF)
    tickDataF.to_csv("./data/tickdata.csv", index=False)
    
    
    print(executionDataF)
    executionDataF.to_csv("./data/execution.csv", index=False)
    
    orderbookDataF.sort_values('Price', inplace=True)
    orderbookDataF.drop_duplicates( inplace=True, ignore_index=True)
    print(orderbookDataF)
    orderbookDataF.to_csv("./data/orderbook.csv", index=False)




