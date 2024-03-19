import pygame
import math
from block import Block

class Grid:
    def __init__(self, width, height, bs) -> None:
        self.width = width
        self.height = height
        self.bs = bs

        self.blocks = []

        for i in range(height):
            row = []
            for j in range(width):
                row.append(Block(j * self.bs, i * self.bs, self.bs, "none"))
            self.blocks.append(row)

    def display(self, screen, image_map, current_block):
        for row in self.blocks:
            for b in row:
                b.display(screen, image_map, current_block)

    def set_type(self, x, y, new_type):
        self.blocks[math.floor(y/self.bs)][math.floor(x/self.bs)].set_type(new_type)

    def get_data_string(self):
        return_string = "|"

        x = 0
        y = 0
        cur_type = ''
        cur_iterations = 0

        while y < len(self.blocks) and x < len(self.blocks[y]):
            if cur_type == self.blocks[y][x].get_type():
                cur_iterations += 1
            else:
                return_string += cur_type + '-' + str(cur_iterations) + '|'
                cur_iterations = 1
                cur_type = self.blocks[y][x].get_type()
            x += 1
            if x == len(self.blocks[y]):
                x = 0
                y += 1
            

        return_string += cur_type + '-' + str(cur_iterations) + '|'
        return return_string[3:]


    