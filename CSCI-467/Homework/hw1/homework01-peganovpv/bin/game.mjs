// Crazy 8's Game
// Author: Petr Peganov

import { generateDeck, shuffle, draw, deal } from '../lib/cards.mjs'

import { question } from 'readline-sync'
import clear from 'clear'
import { readFile } from 'fs'

function checkIfPredefinedFile() {

    const pathToFile = process.argv[2]
    let gameData

    if (pathToFile) {
        try {
            const data = readFile(pathToFile)
            gameData = JSON.parse(data)
        } catch (error) {
            console.error('Error with file', error)
            process.exit(1)
        }
    } else {
        gameData = generateGameSetup()
    }

    return gameData

}

function generateGameSetup() {

    const deck = shuffle(generateDeck());
    const { hands, deck: newDeck } = deal(deck, 2, 5)
    let [starterCard, finalDeck] = draw(newDeck, 1)

    while (starterCard[0].value === '8') {
        finalDeck.unshift(starterCard[0])
        [starterCard, finalDeck] = draw(finalDeck, 1)
    }

    return {
        deck: finalDeck,
        playerHand: hands[0],
        computerHand: hands[1],
        discardPile: starterCard,
        nextPlay: starterCard[0]
    }

}

function displayState(gameData) {
    clear();
    console.log(`
                CR🤪ZY 8's
  -----------------------------------------------
  Next suit/rank to play: ➡️  ${gameData.discardPile[gameData.discardPile.length - 1].value}${gameData.discardPile[gameData.discardPile.length - 1].suit}  ⬅️
  -----------------------------------------------
  Top of discard pile: ${gameData.discardPile[gameData.discardPile.length - 1].value}${gameData.discardPile[gameData.discardPile.length - 1].suit}
  Number of cards left in deck: ${gameData.deck.length}
  -----------------------------------------------
  🤖✋ (computer hand): ${gameData.computerHand.length} cards
  😊✋ (player hand): ${gameData.playerHand.map(card => `${card.value}${card.suit}`).join('  ')}
  -----------------------------------------------
  `);
}

function playerTurn(gameData) {

    displayState(gameData);
    console.log('😊 Player\'s turn...');

    let playable = gameData.playerHand.filter(card => card.suit === gameData.discardPile[gameData.discardPile.length - 1].suit || card.value === gameData.discardPile[gameData.discardPile.length - 1].value || card.value === '8')

    if (playable.length > 0) {
        console.log('Enter the number of the card you would like to play:')
        playable.forEach((card, index) => console.log(`${index + 1}: ${card.value}${card.suit}`))
        let playerChoice = parseInt(question('Your choice: '), 10) - 1
        if (playerChoice >= 0 && playerChoice < playable.length) {
            gameData.discardPile.push(playable[playerChoice])
            gameData.playerHand = gameData.playerHand.filter(card => card !== playable[playerChoice])
            console.log(`You played: ${playable[playerChoice].value}${playable[playerChoice].suit}`)
        } else {
            console.log('Choice is not valid.')
        }
    } else {
        console.log('No playable cards. Drawing from deck...')
        let [newCard, remainingDeck] = draw(gameData.deck, 1)
        gameData.playerHand.push(...newCard)
        gameData.deck = remainingDeck
    }

}

function computerTurn(gameData) {

    displayState(gameData);
    console.log('🤖 Computer\'s turn...');
    let playable = gameData.computerHand.filter(card => card.suit === gameData.discardPile[gameData.discardPile.length - 1].suit || card.value === gameData.discardPile[gameData.discardPile.length - 1].value || card.value === '8')

    if (playable.length > 0) {
        gameData.discardPile.push(playable[0])
        gameData.computerHand = gameData.computerHand.filter(card => card !== playable[0])
        console.log(`Computer played: ${playable[0].value}${playable[0].suit}`)
    } else {
        console.log('Computer has no playable cards. Drawing from deck...')
        let [newCard, remainingDeckAfterDraw] = draw(gameData.deck, 1)
        gameData.computerHand.push(...newCard)
        gameData.deck = remainingDeckAfterDraw
    }

}

function startGame() {
    const gameData = checkIfPredefinedFile();
    playerTurn(gameData);
    computerTurn(gameData);
}

startGame()
