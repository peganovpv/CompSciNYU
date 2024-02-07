// cards.mjs
const suits = {SPADES: '♠️', HEARTS: '❤️', CLUBS: '♣️', DIAMONDS: '♦️'};
const values = {ACE: 'A', KING: 'K', QUEEN: 'Q', JACK: 'J', TEN: '10', NINE: '9', EIGHT: '8', SEVEN: '7', SIX: '6', FIVE: '5', FOUR: '4', THREE: '3', TWO: '2'};

function range(start, end, inc){

    let array = []
    for (let i = start; i <= end; i += inc){
        array.push(i)
    }

    return array
}

function generateDeck(){

    let deck = []
    for (let suit in suits){
        for (let value in values){
            deck.push({suit: suits[suit], value: values[value]})
        }
    }

    return deck
}

function shuffle(deck){

    let shuffled = []
    for (let i = 0; i < deck.length; i++){
        let index = Math.floor(Math.random() * deck.length)
        shuffled.push(deck[index])
        deck.splice(index, 1)
    }

    return shuffled
}

function draw(cardsArray, n = 1){
    let newCards = []
    for (let i = 0; i < n; i++){
        newCards.push(cardsArray.pop())
    }
    let retArray = [
        newCards,
        cardsArray
    ]

    return retArray
}

function deal(cardsArray, numHands, cardsPerHand){

    let deck = cardsArray
    let hands = []

    for (let i = 0; i < numHands; i++){
        let hand = []
        for (let j = 0; j < cardsPerHand; j++){
            let drawn = draw(deck)
            hand.push(drawn[0][0])
            deck = drawn[1]
        }
        hands.push(hand)
    }

    const retObject = {
        hands: hands,
        deck: deck
    }

    return retObject

}

function handToString(hand, sep, numbers = false){

    let string = ''
    for (let i = 0; i < hand.length; i++){
        if (numbers){
            string += `${i + 1}: ${hand[i].value}${hand[i].suit}${sep}`
        } else {
            string += `${hand[i].value}${hand[i].suit}${sep}`
        }
    }

    return string

}

function matchesAnyproperty(obj, matchObj){

    for (let key in matchObj){
        if (obj[key] === matchObj[key]){
            return true
        }
    }

    return false

}

function drawUntilPlayable(deck, matchObject){

    let newDeck = deck.slice()
    let removed = []

    for (let i = newDeck.length - 1; i >= 0; i--){
        if (matchesAnyproperty(newDeck[i], matchObject) || newDeck[i].value === '8'){
            removed = newDeck.splice(i, newDeck.length - i)
            break
        }
    }

    let retArray = [
        newDeck,
        removed
    ]

    return retArray

}

export { range, generateDeck, shuffle, draw, deal, handToString, matchesAnyproperty, drawUntilPlayable }

