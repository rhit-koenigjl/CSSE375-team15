import pygame
import sys
import json
from grid import Grid
from image_manager import ImageManager

config_f = open('app-config.json')
config_data = json.load(config_f)

BLOCK_SIZE = config_data['block-size']
LEVEL_WIDTH, LEVEL_HEIGHT = config_data['level-width'], config_data['level-height']

grid = Grid(LEVEL_WIDTH, LEVEL_HEIGHT, BLOCK_SIZE)
image_manager = ImageManager("asset-log.json")

image_keys = list(image_manager.get_keys())
image_keys.append("none")
image_index = 0

# Initialize Pygame
pygame.init()

# Set up the screen
screen_width, screen_height = BLOCK_SIZE * LEVEL_WIDTH, BLOCK_SIZE * LEVEL_HEIGHT
screen = pygame.display.set_mode((screen_width, screen_height))
pygame.display.set_caption("Minimal Pygame Starter")

# Set up the clock
clock = pygame.time.Clock()

# Main game loop
running = True
mouse_down = False
while running:
    # Event handling
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.MOUSEBUTTONDOWN:
            mouse_down = True
        if event.type == pygame.MOUSEBUTTONUP:
            mouse_down = False
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
                image_index += 1
                image_index %= len(image_keys)

    # Fill the screen with white
    screen.fill((200, 200, 200))  # RGB values for white

    # Game logic goes here
    if mouse_down:
        mx, my = pygame.mouse.get_pos()
        grid.set_type(mx, my, image_keys[image_index])

    # Drawing code goes here
    grid.display(screen, image_manager.get_image_map(), image_keys[image_index]  )

    # Update the display
    pygame.display.flip()

    # Cap the frame rate
    clock.tick(60)

# Quit Pygame and the program
pygame.quit()
sys.exit()